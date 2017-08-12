import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MySubscription } from './my-subscription.model';
import { MySubscriptionPopupService } from './my-subscription-popup.service';
import { MySubscriptionService } from './my-subscription.service';

@Component({
    selector: 'jhi-my-subscription-delete-dialog',
    templateUrl: './my-subscription-delete-dialog.component.html'
})
export class MySubscriptionDeleteDialogComponent {

    mySubscription: MySubscription;

    constructor(
        private mySubscriptionService: MySubscriptionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mySubscriptionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'mySubscriptionListModification',
                content: 'Deleted an mySubscription'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-my-subscription-delete-popup',
    template: ''
})
export class MySubscriptionDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mySubscriptionPopupService: MySubscriptionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.mySubscriptionPopupService
                .open(MySubscriptionDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
