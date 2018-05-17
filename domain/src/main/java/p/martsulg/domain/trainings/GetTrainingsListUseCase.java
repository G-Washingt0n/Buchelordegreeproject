package p.martsulg.domain.trainings;

import java.util.List;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.RequestParams;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class GetTrainingsListUseCase extends UseCase<RequestParams, List<TrainingsFeed>> {

    @Override
    protected Observable<List<TrainingsFeed>> buildUseCase(RequestParams requestParams) {
        return RestService.getInstance().getTrainings(requestParams);
    }
}
