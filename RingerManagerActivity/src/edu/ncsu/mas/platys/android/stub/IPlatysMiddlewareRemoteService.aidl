package edu.ncsu.mas.platys.android.stub;

/**
 * An interface exposing the services provided by the Platys middleware.
 * Applications need to bind to the middleware and invoke appropriate functions.
 * 
 * @author Pradeep Murukannaiah
 */
interface IPlatysMiddlewareRemoteService {
  
  /**
   * Registers the application with the middleware. It returns immediately with
   * a private key for the application. The application should use the private key for all
   * further communication with the middleware.
   * 
   * @param name
   *          Unique name of the application. Platys middleware includes this name 
   *          in all broadcast messages directed to this application.
   * @param description
   *          A brief description of the application shown to the user.
   * @return privateKey - private key for the application. Platys generates a unique
   *         private key for applications. Applications should use this key for
   *         communicating with Platy middleware.
   */
  String registerApplication(String name, String description);
  
  /**
   * Unregisters the application.
   * 
   * @param privateKey
   *          Private key of the applicaion
   */
  void unregisterApplication(String privateKey);
  
  /**
   * Returns the status of the application.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return applicationStatus - applicaion status; one of the values in
   *         {@link edu.ncsu.mas.platys.applications.constants.ApplicationStatus} as String.
   */
  String getAplicationStatus(String privateKey);
  
  /**
   * Get the current place.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return current place, if user's privacy preferences allow the applications
   *         to access it; null, otherwise.
   */
  String getCurrentPlace(String privateKey);

  /**
   * Get the current activities.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of current activities; an empty list is returned if there are
   *         no activities the application can access.
   */
  List<String> getCurrentActivities(String privateKey);
  
  /**
   * Get the list of all places the application is allowed to access.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of all places the application can access.
   */
  List<String> getAllPlaces(String privateKey);
  
  /**
   * Get the list of all activities the application is allowed to access.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of all activities the application can access.
   */
  List<String> getAllActivities(String privateKey);
  
  /**
   * Get the list of social circles a connection (i.e., a friend) of the user
   * belongs to.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @param connectionName
   *          Name of connection whose social circles are to be retrieved
   * @return list of social circles to which the connection belongs; an empty
   *         list is returned if the social circles are unknown.
   */
  List<String> getSocialCircles(String privateKey, String connectionName);

  /**
   * Get the list of social circles with which a place or activity information
   * can be shared. Currently, this feature is not implemented and the function
   * returns a list of all social circles.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @param placeOrActivityName
   *          Name of a place or an activity
   * @return list of social circles to which the place or the activity
   *         information can be shared.
   */
  List<String> getSharableSocialCircles(String privateKey, String placeOrActivityName);

}