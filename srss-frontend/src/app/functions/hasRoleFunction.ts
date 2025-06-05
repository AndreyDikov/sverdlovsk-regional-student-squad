import {User} from '../dto/User';

export function hasRole(user: User, role: string): boolean {
  const roles: Map<string, string> = new Map<string, string>([
    ['fighter', 'боец'],
    ['squad-commander', 'командир отряда'],
    ['staff-officer', 'штабник'],
    ['staff-commander', 'командир штаба']
  ]);
  return user.role === roles.get(role);
}
