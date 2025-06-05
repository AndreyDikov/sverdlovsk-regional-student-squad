// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  resourceServerURL: 'http://localhost:8085',
  eventMicroserviceURL: '/event-microservice',
  userMicroserviceURL: '/user-microservice',
  notificationMicroserviceURL: '/notification-microservice',
  frontendURL: 'http://localhost:4200',
  kcClientID: 'srss-client',
  kcBaseURL: 'http://localhost:8443/realms/srss-realm/protocol/openid-connect',
  bffURI: 'http://localhost:8080',
  redirectURI: 'http://localhost:4200',
  imageMap: {
    'Спорт': 'assets/default-images/events/sport.png',
    'Творческая работа': 'assets/default-images/events/art.png',
    'Социальная работа': 'assets/default-images/events/social.png',
    'Производственная работа': 'assets/default-images/events/production.png',
    'Участие в мероприятиях': 'assets/default-images/events/participation.png',
    'Участие в мероприятиях штаба УрФУ': 'assets/default-images/events/urfu.png'
  }
};
