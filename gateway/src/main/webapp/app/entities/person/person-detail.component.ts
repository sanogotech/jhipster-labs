import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Person } from './person.model';
import { PersonService } from './person.service';
import {MySubscription} from '../my-subscription/my-subscription.model';

@Component({
    selector: 'jhi-person-detail',
    templateUrl: './person-detail.component.html'
})
export class PersonDetailComponent implements OnInit, OnDestroy {

    person: Person;
    mySubscription: MySubscription;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    isSaving: boolean;

    constructor(
        private eventManager: JhiEventManager,
        private personService: PersonService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPeople();
        this.isSaving = false;
    }

    load(id) {
        this.personService.find(id).subscribe((person) => {
            this.person = person;
            this.mySubscription = new MySubscription();
            this.mySubscription.personid = id;
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
    save() {
        this.isSaving = true;
        console.log(JSON.stringify(this.mySubscription));
    }
}
