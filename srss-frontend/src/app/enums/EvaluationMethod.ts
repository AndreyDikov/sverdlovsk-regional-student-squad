export enum EvaluationMethod {
  PLACES = 'PLACES',
  MAN_HOURS = 'MAN_HOURS',
  ATTENDANCE = 'ATTENDANCE'
}


export const EVALUATION_METHOD_LABELS: Record<EvaluationMethod, string> = {
  [EvaluationMethod.PLACES]: 'места',
  [EvaluationMethod.MAN_HOURS]: 'человеко-часы',
  [EvaluationMethod.ATTENDANCE]: 'присутствие'
};
