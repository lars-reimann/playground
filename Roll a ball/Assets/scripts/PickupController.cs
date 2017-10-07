using UnityEngine;
using System.Collections;

public class PickupController : MonoBehaviour {

	private float initialScale;

	void Start() {
		initialScale = transform.localScale.x;
	}

	// Update is called once per frame
	void Update () {
		float scale = initialScale * (1 + 0.25f * Mathf.Sin (Time.time));

		transform.Rotate (new Vector3 (0, 45, 0) * Time.deltaTime, Space.World );
		transform.localScale = new Vector3 (scale, scale, scale);
	}
}
