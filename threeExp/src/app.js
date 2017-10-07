import * as THREE from "three";
import * as dat from "./dat.gui.js";

class View {
    constructor() {

        /**
         * The scene to display.
         */
        this.scene = new THREE.Scene();
        this.scene.background = new THREE.Color(0xffffff);

        /**
         * The camera looking at the scene.
         */
        this.camera = new THREE.PerspectiveCamera(
            75,                                     // Field of view
            window.innerWidth / window.innerHeight, // Aspect ratio
            1,                                      // Near clipping plane
            10000                                   // Far clipping plane
        );
        this.camera.position.z = 1000;

        /**
         * The renderer creating the image on the screen.
         */
        this.renderer = new THREE.WebGLRenderer();
        this.renderer.setSize(window.innerWidth, window.innerHeight);
        document.body.appendChild(this.renderer.domElement);

        const geometry = new THREE.BoxGeometry(200, 200, 200);
        const material = new THREE.MeshLambertMaterial({ color: 0xff0000 });
        this.cube      = new THREE.Mesh(geometry, material);
        this.scene.add(this.cube);

        const directionalLight = new THREE.DirectionalLight( 0xffffff, 0.8 );
        directionalLight.position.set(0, 1, 0);
        directionalLight.target = this.cube;
        this.scene.add(directionalLight);

        const light = new THREE.AmbientLight(0xffffff, 0.5);
        this.scene.add(light);
    }

    animate() {
        requestAnimationFrame(() => this.animate());
        this.cube.rotation.x += 0.01;
        this.cube.rotation.y += 0.01;
        this.renderer.render(this.scene, this.camera);
    }
}

const view = new View();
view.animate();

const gui = new dat.GUI();
gui.add(view.cube.position, 'x');
gui.add(view.cube.position, 'y');
gui.add(view.cube.position, 'z');