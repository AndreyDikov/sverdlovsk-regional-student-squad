import {Category} from './Category';
import {EvaluationMethod} from '../enums/EvaluationMethod';

export interface CategoryEvaluationMethod {
  uuid: string;
  category: Category;
  evaluationMethod: EvaluationMethod;
}
