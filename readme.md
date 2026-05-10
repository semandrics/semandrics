# decote

**decote** stands for **de**terministic **co**re **te**sting.

1. Make the core of your backend service deterministic.
2. Write tests that verify the core business logic in a way that observes all interactions with the outside world at the speed of unit tests.

A test scenario might be defined like this:

```java
class CheckoutServiceTest {

    @Test
    void placesOrderAndSendsConfirmation() {

        Scenario
            .whenCalling(SalesService.placeOrder)
                .with(VALID_CART_ID)
                
            .expectCallTo(CartRepository.get)
                .with(CART_ID)
                .andReturn(VALID_CART)
                
            // ...

            .expectCallTo(PaymentService.charge)
                .with(CART_PAYMENT_INFO)
                .andReturn(CHARGE_SUCCESSFUL)

            // ...
                
            .expectCallTo(EmailService.send)
                .with(CONFIRMATION_MAIL)
                .andReturn(SENT)

            .expectResult(SUCCESS)
            .execute(checkoutService);
    }
}
```

---

## How it works

**Two annotations. That's the whole model.**

- `@CoreEntry` — marks a service class as an entry point into the core. This is where a request, command, or query enters.
- `@CoreBoundary` — marks an interface as a boundary between the core and the outside world.

The implementation of a boundary lives outside the core package. The core never imports it.

**Hidden side effects break determinism silently.** `LocalDateTime.now()` or `UUID.randomUUID()` buried in a service
method mean the same input produces different outputs depending on when you call it. decote ships a small standard
library of boundary interfaces for the common ones — `TimeService`, `RandomService` — injected the same way as any other
dependency.

---

## Step by step

### Define what is your `Core`

The `Core` as that part of your service that is agnostic of the API and infrastructure.

Usually it starts right behind the API, after the API layer has done a basic validation and mapped the request models
to domain models. Those are specific services and methods that are called. Those are the `Entry Points` of the `Core`. 

At some point the `Core` interacts with a database or external services. Here we are leaving the Core. Those are the
`Boundaries` of the `Core`.

```
┌─────────────────────────────────────────────────────────────────┐
│                            API Layer                            │
│                                                                 │
│  - HTTP Controllers                                             │
│  - Message Consumers                                            │
│  - Basic validation                                             │
│  - Mapping of API models to domain models                       │
└────────────────────────────────┬────────────────────────────────┘
                                 │
                                 ▼
┌─────────────────────────────────────────────────────────────────┐
│                              Core                               │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │ EntryPoints                                               │  │
│  │                                                           │  │
│  │  - What are the ways to interact with your system,        │  │
│  │    independent of HTTP, REST, etc.                        │  │
│  │  - Start of deterministic execution                       │  │
│  └───────────────────────────────────────────────────────────┘  │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │ Business Logic                                            │  │
│  │                                                           │  │
│  │  - Pure decision-making                                   │  │
│  │  - Applcation logic(orchestration, transaction handling)  │  │
│  │  - Domain rules                                           │  │
│  └───────────────────────────────────────────────────────────┘  │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │ Boundaries                                                │  │
│  │                                                           │  │
│  │  - Interfaces to external world                           │  │
│  └───────────────────────────────────────────────────────────┘  │
└────────────────────────────────┬────────────────────────────────┘
                                 │
                                 ▼
┌─────────────────────────────────────────────────────────────────┐
│                        Infrastructure Layer                     │
│                                                                 │
│  - Database (JPA, SQL, etc.)                                    │
│  - External APIs (HTTP clients)                                 │
│  - Messaging systems (Kafka, RabbitMQ, etc.)                    │
│  - Time / randomness providers                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

### 2. Annotate your `Entry Points`

Mark the specific classes through which a client interacts with your service. 

```java
@CoreEntry  // <- this marks the class as an entry point
@Service
public class SalesService {

    private final CartRepository catalog;
    private final EmailService email;
    private final TimeService timeService; // from decote stdlib

    public OrderResult placeOrder(Cart cart) {
        // pure decision logic
    }
}
```
---

### 3. Annotate your boundary interfaces

One interface per external system, inside the core package.

```java
@CoreBoundary
public interface EmailService {
    SendResult send(EmailMessage message);
}
```

The implementation — the actual SMTP client, the JPA repository — lives outside. The core never sees it.

---

### 4. Root out hidden side effects

To make the core deterministic, we need to replace all the left side effects with deterministic alternatives.
(Ignore logging, tracing, and metrics for obvious reasons.)

```java
// ✗  breaks determinism
var currentTime = LocalDateTime.now();
var id = UUID.randomUUID().toString();

// ✓  injected boundary — controllable in tests
var currentTime = timeService.getCurrentLocalDateTime();
var id = randomService.createUuid();
```

---

### 5. Write your tests

```java
class CheckoutServiceTest {

    @Test
    void placesOrderAndSendsConfirmation() {

        Scenario
                .whenCalling(SalesService.placeOrder)
                .with(VALID_CART_ID)

                .expectCallTo(CartRepository.get)
                .with(CART_ID)
                .andReturn(VALID_CART)

                // ...

                .expectCallTo(PaymentService.charge)
                .with(CART_PAYMENT_INFO)
                .andReturn(CHARGE_SUCCESSFUL)

                // ...

                .expectCallTo(EmailService.send)
                .with(CONFIRMATION_MAIL)
                .andReturn(SENT)

                .expectResult(SUCCESS)
                .execute(checkoutService);
    }
}
```

No containers. No wiring. The boundary interfaces are the test surface.

---

## Where this fits
|                                              | Unit tests                                           | decote                                   | E2E / integration                 |
|----------------------------------------------|------------------------------------------------------|-------------------------------------------|-----------------------------------|
| What is verified                             | Collaboration between implementation units           | Business process through the core         | System behavior with real systems |
| Refactoring impact                           | High                                                 | Very low                                  | Very high                        |
| No infrastructure                            | ✓                                                    | ✓                                         | —                                 |
| Fast                                         | ✓                                                    | ✓                                         | —                                 |
| Deterministic by design                      | —                                                    | ✓                                         | —                                 |
| Focus on business processes                  | —                                                    | ✓                                         | ✓ (with heavy overhead)          |
| Tests full service flow                      | —                                                    | ✓                                         | ✓                                 |
| Tests real integration with external systems | —                                                    | —                                         | ✓                                 |
decote fills the gap between unit tests and end-to-end tests.
It is not a replacement for either — it is the layer in-between.

---

## Status & Roadmap

Not published yet. The annotation model and test scenario API are designed; implementation is in progress. Watch the repo for updates.
Planned: the testing library itself, a Kotlin implementation, and an AI-assisted IntelliJ plugin for boundary validation, side effect detection, and test scaffolding.
---

## License

MIT
