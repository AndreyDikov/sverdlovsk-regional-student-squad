import {Gender} from '../enums/Gender';

export interface AdditionalUserData {
  uuid: string;
  userUuid: string;
  squadUuid: string | null;
  gender: Gender;
  score: number;
}
