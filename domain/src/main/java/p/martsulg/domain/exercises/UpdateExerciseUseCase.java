package p.martsulg.domain.exercises;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 13.06.2018.
 */

public class UpdateExerciseUseCase extends UseCase<ExercisesFeed, ExercisesFeed> {
    @Override
    protected Observable<ExercisesFeed> buildUseCase(ExercisesFeed feed) {
        return RestService.getInstance().updateExercise(feed);
    }
}
