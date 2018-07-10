package p.martsulg.domain.sets;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.RequestRelation;
import p.martsulg.domain.UseCase;

public class SetRelationUseCase extends UseCase<RequestRelation, Integer> {

    @Override
    protected Observable<Integer> buildUseCase(RequestRelation requestRelation) {
        return RestService.getInstance().addSetRelation(requestRelation);
    }
}
