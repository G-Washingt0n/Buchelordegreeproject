package p.martsulg.domain;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.DeleteResponse;
import p.martsulg.data.models.RequestParams;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class DelItemUseCase extends UseCase<RequestParams, DeleteResponse> {

    @Override
    protected Observable<DeleteResponse> buildUseCase(RequestParams params) {
        return RestService.getInstance().delItem(params);
    }
}
