import {Event} from './Event';

export interface NewEvent {
  eventDto: Event;
  evaluationMethod: string;
  categoryUuid: string;
}
