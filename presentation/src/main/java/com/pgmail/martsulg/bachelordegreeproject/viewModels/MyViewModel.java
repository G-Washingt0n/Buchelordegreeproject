package com.pgmail.martsulg.bachelordegreeproject.viewModels;

/**
 * Created by g_washingt0n on 08.02.2018.
 */

public interface MyViewModel {

    public void init();

    public void release();

    public void resume();

    public void pause();

    public void getRequest();

    public void delRequest(String objectId, int position);

    public void addRequest();

}
