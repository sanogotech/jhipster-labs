import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { MySubscriptionComponent } from './my-subscription.component';
import { MySubscriptionDetailComponent } from './my-subscription-detail.component';
import { MySubscriptionPopupComponent } from './my-subscription-dialog.component';
import { MySubscriptionDeletePopupComponent } from './my-subscription-delete-dialog.component';

import { Principal } from '../../shared';

export const mySubscriptionRoute: Routes = [
    {
        path: 'my-subscription',
        component: MySubscriptionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.mySubscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'my-subscription/:id',
        component: MySubscriptionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.mySubscription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mySubscriptionPopupRoute: Routes = [
    {
        path: 'my-subscription-new',
        component: MySubscriptionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.mySubscription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'my-subscription/:id/edit',
        component: MySubscriptionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.mySubscription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'my-subscription/:id/delete',
        component: MySubscriptionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.mySubscription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
