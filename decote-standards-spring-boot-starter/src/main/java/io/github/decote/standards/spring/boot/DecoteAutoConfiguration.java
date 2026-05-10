package io.github.decote.standards.spring.boot;

import io.github.decote.std.random.DefaultRandomService;
import io.github.decote.std.random.RandomService;
import io.github.decote.std.time.SystemTimeService;
import io.github.decote.std.time.TimeService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@AutoConfiguration
@ConditionalOnClass({TimeService.class, RandomService.class})
public class DecoteAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TimeService timeService() {
        return new SystemTimeService();
    }

    @Bean
    @ConditionalOnMissingBean
    public RandomService randomService() {
        return new DefaultRandomService();
    }
}
