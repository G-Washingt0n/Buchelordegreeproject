package p.martsulg.domain.trainings;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 13.06.2018.
 */

public class UpdateTrainingUseCase extends UseCase<TrainingsFeed, TrainingsFeed> {
    @Override
    protected Observable<TrainingsFeed> buildUseCase(TrainingsFeed feed) {
        return RestService.getInstance().updateTraining(feed);
    }
}
