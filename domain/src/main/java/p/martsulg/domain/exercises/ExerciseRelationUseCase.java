package p.martsulg.domain.exercises;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.RequestRelation;
import p.martsulg.domain.UseCase;

public class ExerciseRelationUseCase extends UseCase<RequestRelation, Integer> {

    @Override
    protected Observable<Integer> buildUseCase(RequestRelation requestRelation) {
        return RestService.getInstance().addRelation(requestRelation);
    }
}
