import {AdditionalUserData} from './AdditionalUserData';

export interface FullUser {
  uuid: string;
  name: string;
  surname: string;
  email: string;
  password: string | null;
  role: string;
  additionalUserData: AdditionalUserData;
}
