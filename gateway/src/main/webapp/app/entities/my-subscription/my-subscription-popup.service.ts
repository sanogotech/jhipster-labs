import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MySubscription } from './my-subscription.model';
import { MySubscriptionService } from './my-subscription.service';

@Injectable()
export class MySubscriptionPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private mySubscriptionService: MySubscriptionService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.mySubscriptionService.find(id).subscribe((mySubscription) => {
                if (mySubscription.date) {
                    mySubscription.date = {
                        year: mySubscription.date.getFullYear(),
                        month: mySubscription.date.getMonth() + 1,
                        day: mySubscription.date.getDate()
                    };
                }
                this.mySubscriptionModalRef(component, mySubscription);
            });
        } else {
            return this.mySubscriptionModalRef(component, new MySubscription());
        }
    }

    mySubscriptionModalRef(component: Component, mySubscription: MySubscription): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.mySubscription = mySubscription;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
