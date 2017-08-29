import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Person} from './person.model';
import {Observable} from 'rxjs/Observable';
@Injectable()
export class CustomService {
    constructor(private http: Http) {

    }

    batchDelete(persons: Person[]): Observable<any> {
        return this.http.post('/person/api/deleteperson', persons);
    }

}
