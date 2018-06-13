package p.martsulg.domain.exercises;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class AddExerciseUseCase extends UseCase<ExercisesFeed, ExercisesFeed> {
    @Override
    protected Observable<ExercisesFeed> buildUseCase(ExercisesFeed feed) {
        return RestService.getInstance().newExercise(feed);
    }
}
