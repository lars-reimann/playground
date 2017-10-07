using UnityEngine;
using UnityEngine .UI;
using System.Collections;

public class PlayerController : MonoBehaviour {

	public float speed;
    public Text countText;
    public Text victoryText;

	private Rigidbody rb;
    private int count;

	void Start() {
		rb = GetComponent<Rigidbody> ();
	    count = 0;
	    SetCountText();
	}

    private void SetCountText()
    {
        countText.text = "Count: " + count;
    }

    void FixedUpdate() {
		float moveHorizontal = Input.GetAxis ("Horizontal") * speed;
		float moveVertical = Input.GetAxis ("Vertical") * speed;
		rb.AddForce (moveHorizontal, 0, moveVertical);
	}

	void OnTriggerEnter(Collider other) {
	    if (other.gameObject.CompareTag("Pickup"))
	    {
	        other.gameObject.SetActive(false);
	        count++;
            SetCountText();
	        if (count == 11)
	        {
	            victoryText.gameObject.SetActive(true);
	        }
        }
	}
}
