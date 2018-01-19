package th.co.thiensurat.ecatalog.firebase;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by teerayut.k on 1/12/2018.
 */

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
