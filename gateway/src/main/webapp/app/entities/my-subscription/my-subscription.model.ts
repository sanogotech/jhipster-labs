import { BaseEntity } from './../../shared';

export class MySubscription implements BaseEntity {
    constructor(
        public id?: number,
        public label?: string,
        public date?: any,
        public ownerLogin?: string,
    ) {
    }
}
