export enum SquadsSortedType {
  RATING_ASC = 'RATING_ASC',
  RATING_DESC = 'RATING_DESC',
  NUMBER_USERS_ASC = 'NUMBER_USERS_ASC',
  NUMBER_USERS_DESC = 'NUMBER_USERS_DESC',
  ALPHABET = 'ALPHABET'
}


export const SQUADS_SORTED_TYPE_LABELS: Record<SquadsSortedType, string> = {
  [SquadsSortedType.RATING_ASC]: 'рейтингу ↑',
  [SquadsSortedType.RATING_DESC]: 'рейтингу ↓',
  [SquadsSortedType.NUMBER_USERS_ASC]: 'участникам ↑',
  [SquadsSortedType.NUMBER_USERS_DESC]: 'участникам ↓',
  [SquadsSortedType.ALPHABET]: 'названию'
};
