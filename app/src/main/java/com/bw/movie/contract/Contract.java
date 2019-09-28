package com.bw.movie.contract;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;

import java.util.Map;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:17
 * function：
 */
public interface Contract {
    interface HomeView extends IBaseView {
    }

    abstract class ContractPresenter extends BasePresenter {

        public ContractPresenter(IBaseView iBaseView) {
            super(iBaseView);
        }
        public abstract void getData(Map<String, String> hmap);
    }
}
