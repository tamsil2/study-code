package me.kobeshow.kubernetes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlPlaneController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    LocalHostService localHostService;

    @GetMapping("/block")
    public String block() {
        AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.REFUSING_TRAFFIC);
        return "Blocked requests " + localHostService.getLocalHostInfo();
    }

    @GetMapping("/turnoff")
    public String turnOff() {
        AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.BROKEN);
        return "Broken " + localHostService.getLocalHostInfo();
    }

    @Async
    @EventListener
    public void onStateChanged(AvailabilityChangeEvent<ReadinessState> readiness) throws InterruptedException {
        System.out.println("State is changed to " + readiness.getState());
        if(readiness.getState() == ReadinessState.REFUSING_TRAFFIC) {
            Thread.sleep(15000L);
            AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.ACCEPTING_TRAFFIC);
        }
    }
}
