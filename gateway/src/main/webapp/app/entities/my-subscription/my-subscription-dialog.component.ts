import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MySubscription } from './my-subscription.model';
import { MySubscriptionPopupService } from './my-subscription-popup.service';
import { MySubscriptionService } from './my-subscription.service';

@Component({
    selector: 'jhi-my-subscription-dialog',
    templateUrl: './my-subscription-dialog.component.html'
})
export class MySubscriptionDialogComponent implements OnInit {

    mySubscription: MySubscription;
    authorities: any[];
    isSaving: boolean;
    dateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private mySubscriptionService: MySubscriptionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.mySubscription.id !== undefined) {
            this.subscribeToSaveResponse(
                this.mySubscriptionService.update(this.mySubscription));
        } else {
            this.subscribeToSaveResponse(
                this.mySubscriptionService.create(this.mySubscription));
        }
    }

    private subscribeToSaveResponse(result: Observable<MySubscription>) {
        result.subscribe((res: MySubscription) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: MySubscription) {
        this.eventManager.broadcast({ name: 'mySubscriptionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-my-subscription-popup',
    template: ''
})
export class MySubscriptionPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mySubscriptionPopupService: MySubscriptionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.mySubscriptionPopupService
                    .open(MySubscriptionDialogComponent, params['id']);
            } else {
                this.modalRef = this.mySubscriptionPopupService
                    .open(MySubscriptionDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
