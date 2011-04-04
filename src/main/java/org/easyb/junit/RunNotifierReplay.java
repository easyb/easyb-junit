package org.easyb.junit;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;

import java.util.ArrayList;
import java.util.List;

public class RunNotifierReplay extends RunNotifier {
   private class Event {
      String method;
      Description description;
      Failure failure;
      long time = System.currentTimeMillis();

      public Event(String method, Description description) {
         this.method = method;
         this.description = description;
      }

      public Event(String method, Failure failure) {
         this.method = method;
         this.failure = failure;
      }
   }

   private List<Event> events = new ArrayList<Event>();

   public void fireTestStarted(Description description) throws StoppedByUserException {
      events.add(new Event("start", description));
   }

   public void fireTestFinished(Description description) {
      events.add(new Event("finish", description));
   }

   public void fireTestFailure(Failure failure) {
      events.add(new Event("fail", failure));
   }

   public void replay(RunNotifier notifier, boolean trackTime) {
      long start = 0;
      for (Event event : events) {
         if (event.method.equals("start")) {
            notifier.fireTestStarted(event.description);
            start = event.time;
         } else if (event.method.equals("finish")) {
            if (trackTime)
               sleep(event.time - start);
            notifier.fireTestFinished(event.description);
         } else {
            notifier.fireTestFailure(event.failure);
         }
      }
   }

   private void sleep(long time) {
      try {
         Thread.sleep(time);
      } catch (InterruptedException e) {}
   }
}
