# Semandrics

Semandrics is an exploration into a different way of thinking about backend testing.

The central idea is simple:

> If a backend service is isolated into a deterministic Core, and the passed in data as well es the responses from 
> external systems are semantically categorized, then its meaningful behavioral flows become enumerable.

Instead of writing tests around arbitrary concrete values, Semandrics models the semantic categories that actually 
influence behavior — called **Variants** — and composes them into fully specified **Scenarios**. Properly defined 
Variants enable to ensure that the scenarios cover all meaningful behavioral flows, and that they are robust against
irrelevant changes in the data.

Tests can automatically be generated from the scenarios and run at unit test speed. The scenarios themselves can be used
as documentation and a common language for discussing the system's behavior.

The project is currently experimental and research-oriented.  
This repository is a place for exploring the idea, refining the language around it, and turning the model into working
tooling.

---

More information on the website: [semandrics.github.io/semandrics](https://semandrics.github.io/semandrics)

---

License:
[MIT](license.md)