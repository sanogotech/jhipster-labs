import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { MySubscription } from './my-subscription.model';
import { MySubscriptionService } from './my-subscription.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-my-subscription',
    templateUrl: './my-subscription.component.html'
})
export class MySubscriptionComponent implements OnInit, OnDestroy {
mySubscriptions: MySubscription[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private mySubscriptionService: MySubscriptionService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.mySubscriptionService.query().subscribe(
            (res: ResponseWrapper) => {
                this.mySubscriptions = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInMySubscriptions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: MySubscription) {
        return item.id;
    }
    registerChangeInMySubscriptions() {
        this.eventSubscriber = this.eventManager.subscribe('mySubscriptionListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
