package commonutil;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.http.HttpServlet;
import java.util.Date;

public class JobScheduler extends HttpServlet
{
    public void init()
    {
        int pollingTimeSec = 30;

        try
        {
            JobDetail job = JobBuilder.newJob(AddPollingDeviceJob.class).build();

            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(pollingTimeSec).repeatForever()).build();

            SchedulerFactory schFactory = new StdSchedulerFactory();

            Scheduler sch = schFactory.getScheduler();

            sch.start();

            sch.scheduleJob(job, trigger);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
