import { BaseEntity } from './../../shared';

export class Car implements BaseEntity {
    constructor(
        public id?: number,
        public model?: string,
        public color?: string,
        public personId?: number,
    ) {
    }
}
