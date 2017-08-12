import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { MySubscription } from './my-subscription.model';
import { MySubscriptionService } from './my-subscription.service';

@Component({
    selector: 'jhi-my-subscription-detail',
    templateUrl: './my-subscription-detail.component.html'
})
export class MySubscriptionDetailComponent implements OnInit, OnDestroy {

    mySubscription: MySubscription;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private mySubscriptionService: MySubscriptionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMySubscriptions();
    }

    load(id) {
        this.mySubscriptionService.find(id).subscribe((mySubscription) => {
            this.mySubscription = mySubscription;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMySubscriptions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'mySubscriptionListModification',
            (response) => this.load(this.mySubscription.id)
        );
    }
}
