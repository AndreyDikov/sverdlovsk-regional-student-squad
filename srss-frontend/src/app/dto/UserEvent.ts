export interface UserEvent {
  uuid: string;
  eventWeightUuid: string;
  eventUuid: string;
  userUuid: string;
  comment: string | null;
  eventRating: number | null;
  userScore: number | null;
}
