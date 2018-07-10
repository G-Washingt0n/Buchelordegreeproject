package p.martsulg.domain.sets;

import java.util.List;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.RequestParams;
import p.martsulg.data.models.SetsFeed;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class GetSetsListUseCase extends UseCase<RequestParams, ExercisesFeed> {
    @Override
    protected Observable<ExercisesFeed> buildUseCase(RequestParams requestParams) {
        return RestService.getInstance().getSets(requestParams);
    }
}
