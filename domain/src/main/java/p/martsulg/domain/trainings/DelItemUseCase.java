package p.martsulg.domain.trainings;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.RequestParams;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class DelItemUseCase extends UseCase<RequestParams, Long> {

    @Override
    protected Observable<Long> buildUseCase(RequestParams params) {
        return RestService.getInstance().delItem(params);
    }
}
