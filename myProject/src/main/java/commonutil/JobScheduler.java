package commonutil;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.http.HttpServlet;
import java.util.Date;

public class JobScheduler extends HttpServlet
{
    public void init()
    {
        try
        {
            JobDetail job = JobBuilder.newJob(AddPollingDeviceJob.class).build();

            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30).repeatForever()).build();

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
