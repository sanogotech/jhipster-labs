import { BaseEntity } from './../../shared';

const enum Gender {
    'MALE',
    'FEMALE'
}

export class Person implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public surname?: string,
        public gender?: Gender,
        public cars?: BaseEntity[],
    ) {
    }
}
