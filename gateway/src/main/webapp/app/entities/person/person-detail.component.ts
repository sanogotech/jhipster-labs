import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Person } from './person.model';
import { PersonService } from './person.service';
import {MySubscription , MySubscriptionService} from '../my-subscription/index';
import {ResponseWrapper} from '../../shared/model/response-wrapper.model';

@Component({
    selector: 'jhi-person-detail',
    templateUrl: './person-detail.component.html'
})
export class PersonDetailComponent implements OnInit, OnDestroy {

    person: Person;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    mySubscriptions: MySubscription[];

    constructor(
        private eventManager: JhiEventManager,
        private personService: PersonService,
        private route: ActivatedRoute,
        private mySubscriptionService: MySubscriptionService,

    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPeople();
    }

    loadSubscription(personId: number) {
        this.mySubscriptionService.getPersonSubscription(personId).subscribe(
            (res: ResponseWrapper) => { this.mySubscriptions = res.json }
        );
    }

    load(id) {
        this.personService.find(id).subscribe((person) => {
            this.person = person;
            this.loadSubscription(id);
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPeople() {
        this.eventSubscriber = this.eventManager.subscribe(
            'personListModification',
            (response) => this.load(this.person.id)
        );
    }
}
