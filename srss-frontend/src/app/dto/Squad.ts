export interface Squad {
  uuid: string | null;
  commanderUuid: string | null;
  name: string;
  description: string;
  score: number | null;
  isClosed: boolean | null;
  numberUsers: number | null;
}
