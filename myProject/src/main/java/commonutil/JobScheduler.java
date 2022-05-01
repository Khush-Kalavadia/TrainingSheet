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
            // specify the job' s details..
            JobDetail job = JobBuilder.newJob(AddPollingDeviceJob.class).build();

            // specify the running period of the job
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30).repeatForever()).build();

            //schedule the job
            SchedulerFactory schFactory = new StdSchedulerFactory();

            Scheduler sch = schFactory.getScheduler();

            sch.start();

//            System.out.println(new Date() + " : JobScheduler started");

            sch.scheduleJob(job, trigger);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
