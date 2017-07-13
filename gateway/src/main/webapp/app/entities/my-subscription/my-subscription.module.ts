import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from '../../shared';
import {
    MySubscriptionService,
    MySubscriptionPopupService,
    MySubscriptionComponent,
    MySubscriptionDetailComponent,
    MySubscriptionDialogComponent,
    MySubscriptionPopupComponent,
    MySubscriptionDeletePopupComponent,
    MySubscriptionDeleteDialogComponent,
    mySubscriptionRoute,
    mySubscriptionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...mySubscriptionRoute,
    ...mySubscriptionPopupRoute,
];

@NgModule({
    imports: [
        GatewaySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MySubscriptionComponent,
        MySubscriptionDetailComponent,
        MySubscriptionDialogComponent,
        MySubscriptionDeleteDialogComponent,
        MySubscriptionPopupComponent,
        MySubscriptionDeletePopupComponent,
    ],
    entryComponents: [
        MySubscriptionComponent,
        MySubscriptionDialogComponent,
        MySubscriptionPopupComponent,
        MySubscriptionDeleteDialogComponent,
        MySubscriptionDeletePopupComponent,
    ],
    providers: [
        MySubscriptionService,
        MySubscriptionPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayMySubscriptionModule {}
