export interface Event {
  uuid: string | null;
  authorUuid: string | null;
  categoryEvaluationMethodUuid: string | null;
  name: string;
  description: string;
  startAt: string;
  endAt: string;
  place: string;
  averageRating: number | null;
  numberUsers: number | null;
}
