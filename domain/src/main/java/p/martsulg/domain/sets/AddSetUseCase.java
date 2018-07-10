package p.martsulg.domain.sets;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.SetsFeed;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class AddSetUseCase extends UseCase<SetsFeed, SetsFeed> {
    @Override
    protected Observable<SetsFeed> buildUseCase(SetsFeed feed) {
        return RestService.getInstance().newSet(feed);
    }
}
