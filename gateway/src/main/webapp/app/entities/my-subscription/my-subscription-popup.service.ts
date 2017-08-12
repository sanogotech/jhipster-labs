import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MySubscription } from './my-subscription.model';
import { MySubscriptionService } from './my-subscription.service';

@Injectable()
export class MySubscriptionPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private mySubscriptionService: MySubscriptionService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.mySubscriptionService.find(id).subscribe((mySubscription) => {
                    if (mySubscription.date) {
                        mySubscription.date = {
                            year: mySubscription.date.getFullYear(),
                            month: mySubscription.date.getMonth() + 1,
                            day: mySubscription.date.getDate()
                        };
                    }
                    this.ngbModalRef = this.mySubscriptionModalRef(component, mySubscription);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.mySubscriptionModalRef(component, new MySubscription());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    mySubscriptionModalRef(component: Component, mySubscription: MySubscription): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.mySubscription = mySubscription;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
