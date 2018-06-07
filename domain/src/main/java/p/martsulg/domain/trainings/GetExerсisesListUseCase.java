package p.martsulg.domain.trainings;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.RequestParams;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class GetExerсisesListUseCase extends UseCase<RequestParams, TrainingsFeed> {
    @Override
    protected Observable<TrainingsFeed> buildUseCase(RequestParams requestParams) {
        return RestService.getInstance().getExercises(requestParams);
    }
}
