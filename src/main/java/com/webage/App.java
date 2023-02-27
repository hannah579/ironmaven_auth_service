package com.webage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

	public static void main(String[] args) {
		System.out.println("starting auth service now");
		SpringApplication.run(App.class, args);
	}

//@Bean
//public io.opentracing.Tracer jaegerTracer() {
//    return new Configuration("auth-api", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
//        new Configuration.ReporterConfiguration())
//        .getTracer();
//}
}
