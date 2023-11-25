package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.Event;
import org.firstinspires.ftc.robotcore.external.State;
import org.firstinspires.ftc.robotcore.external.StateMachine;
import org.firstinspires.ftc.robotcore.external.StateTransition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class FSMSampleTest {
    private static final int GREEN_MS = 10000;
    private static final int AMBER_MS = 500;
    private static final int RED_MS = 8000;


    private class TrafficLightState implements State {
        @Override
        public void onEnter(Event event) {
            System.out.println(this + " on");
        }

        @Override
        public void onExit(Event event) {
            System.out.println(this + " off");
        }

        @NonNull
        public String toString()
        {
            return getClass().getSimpleName();
        }
    }

    private class Green extends TrafficLightState {}
    private final Green Green = new Green();
    private class Amber extends TrafficLightState {}
    private final Amber Amber = new Amber();
    private class Red extends TrafficLightState {}
    private final Red Red = new Red();

    enum TrafficLightEvent implements Event {
        RED_TO_AMBER,
        AMBER_TO_GREEN,
        GREEN_TO_AMBER,
        AMBER_TO_RED;

        @Override
        public String getName()
        {
            return this.toString();
        }
    }

    private class TrafficLight extends StateMachine {
        TrafficLight() {
            addTransition(new StateTransition(Green, TrafficLightEvent.GREEN_TO_AMBER, new Amber()));
            addTransition(new StateTransition(new Amber(), TrafficLightEvent.AMBER_TO_RED, new Red()));
            addTransition(new StateTransition(new Red(), TrafficLightEvent.RED_TO_AMBER, new Amber()));
            addTransition(new StateTransition(new Amber(), TrafficLightEvent.AMBER_TO_GREEN, new Green()));
        }

        public void start(State initialState) {
            super.start(initialState);
        }

        public State getCurrentState() {
            return super.currentState;
        }
    }


    FSMSampleTest() {
    }
    @Test
    public void testTrafficLight() {
        TrafficLight trafficLight = new TrafficLight();

        trafficLight.addTransition(new StateTransition(new Green(), TrafficLightEvent.GREEN_TO_AMBER, new Amber()));
        trafficLight.addTransition(new StateTransition(new Amber(), TrafficLightEvent.AMBER_TO_RED, new Red()));
        trafficLight.addTransition(new StateTransition(new Red(), TrafficLightEvent.RED_TO_AMBER, new Amber()));
        trafficLight.addTransition(new StateTransition(new Amber(), TrafficLightEvent.AMBER_TO_GREEN, new Green()));

        trafficLight.start(Green);
//        Assertions.assertEquals(Green.toString(), trafficLight.getCurrentState().toString());
//        trafficLight.consumeEvent(TrafficLightEvent.GREEN_TO_AMBER);
//        Assertions.assertEquals(Amber.toString(), trafficLight.getCurrentState().toString());
//        trafficLight.consumeEvent(TrafficLightEvent.AMBER_TO_RED);
//        Assertions.assertEquals(Red.toString(), trafficLight.getCurrentState().toString());
//        trafficLight.consumeEvent(TrafficLightEvent.RED_TO_AMBER);
//        Assertions.assertEquals(Amber.toString(), trafficLight.getCurrentState().toString());
//        trafficLight.consumeEvent(TrafficLightEvent.AMBER_TO_GREEN);
//        Assertions.assertEquals(Green.toString(), trafficLight.getCurrentState().toString());
//        trafficLight.consumeEvent(TrafficLightEvent.RED_TO_AMBER);
//        Assertions.assertEquals(Amber.toString(), trafficLight.getCurrentState().toString());
    }
}
