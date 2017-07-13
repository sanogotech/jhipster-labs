/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { GatewayTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { MySubscriptionDetailComponent } from '../../../../../../main/webapp/app/entities/my-subscription/my-subscription-detail.component';
import { MySubscriptionService } from '../../../../../../main/webapp/app/entities/my-subscription/my-subscription.service';
import { MySubscription } from '../../../../../../main/webapp/app/entities/my-subscription/my-subscription.model';

describe('Component Tests', () => {

    describe('MySubscription Management Detail Component', () => {
        let comp: MySubscriptionDetailComponent;
        let fixture: ComponentFixture<MySubscriptionDetailComponent>;
        let service: MySubscriptionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [MySubscriptionDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    MySubscriptionService,
                    JhiEventManager
                ]
            }).overrideTemplate(MySubscriptionDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MySubscriptionDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MySubscriptionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new MySubscription(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.mySubscription).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
