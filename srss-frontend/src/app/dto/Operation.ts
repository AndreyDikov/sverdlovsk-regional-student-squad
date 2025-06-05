import {HttpMethod} from '../enums/HttpMethod';

export interface Operation {
  method: HttpMethod;
  url: string;
  body: any;
}
