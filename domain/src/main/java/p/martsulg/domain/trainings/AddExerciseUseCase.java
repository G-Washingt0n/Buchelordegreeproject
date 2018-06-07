package p.martsulg.domain.trainings;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class AddExerciseUseCase extends UseCase<ExercisesFeed, Void> {
    @Override
    protected Observable<Void> buildUseCase(ExercisesFeed feed) {
        return RestService.getInstance().newExercise(feed);
    }
}
