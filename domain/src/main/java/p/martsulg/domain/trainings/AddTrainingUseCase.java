package p.martsulg.domain.trainings;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class AddTrainingUseCase extends UseCase<TrainingsFeed, Void> {
    @Override
    protected Observable<Void> buildUseCase(TrainingsFeed feed) {
        return RestService.getInstance().newTraining(feed);
    }
}
